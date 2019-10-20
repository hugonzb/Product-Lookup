"use strict";

class SaleItem {

    constructor(product, quantity) {
        // only set the fields if we have a valid product
        if (product) {
            this.product = product;
            this.quantityPurchased = quantity;
            this.salePrice = product.listPrice;
        }
    }

    getItemTotal() {
        return this.salePrice * this.quantityPurchased;
    }

}

class ShoppingCart {

    constructor() {
        this.items = new Array();
    }

    reconstruct(sessionData) {
        for (let item of sessionData.items) {
            this.addItem(Object.assign(new SaleItem(), item));
        }
        
    }

    getItems() {
        return this.items;
    }

    addItem(item) {
        this.items.push(item);
    }

    setCustomer(customer) {
        this.customer = customer;
    }

    getTotal() {
        let total = 0;
        for (let item of this.items) {
            total += item.getItemTotal();
        }
        return total;
    }
    

}

// create a new module, and load the other pluggable modules
var module = angular.module('ShoppingApp', ['ngResource', 'ngStorage']);

module.factory('productDAO', function ($resource) {
    return $resource('/api/products/:id');
});
module.factory('categoryDAO', function ($resource) {
    return $resource('/api/categories/:category');
});

module.factory('registerDAO', function ($resource) {
    return $resource('/api/register');
});

module.factory('signInDAO', function ($resource) {
    return $resource('/api/customers/:username');
});

module.factory('saleDAO', function ($resource) {
    return $resource('/api/sales');
});

module.controller('CartController', function (saleDAO, cart, $sessionStorage, $window) {
    this.items = cart.getItems();
    this.total = cart.getTotal();
    this.SelectProduct = function (product) {          
        $sessionStorage.selectedProduct = product;
        $window.location = 'quantitytopurchase.html';               
    };  
    this.addtoCart = function (quantitySpecified) {
        this.selectedProduct = $sessionStorage.selectedProduct;
        let saleItem = new SaleItem(this.selectedProduct, quantitySpecified);
        cart.addItem(saleItem);
        $sessionStorage.cart = cart;
        $window.location = 'products.html'; 
    };
    this.checkoutCart = function (){
        this.customer = $sessionStorage.customer;
        cart.setCustomer(this.customer);
        saleDAO.save(cart);
        delete $sessionStorage.cart;
        $window.location = 'ordercompleted.html'; 
    };
});

module.controller('ProductController', function (productDAO, categoryDAO, $sessionStorage) {
    this.products = productDAO.query();
    // load the categories
    this.categories = categoryDAO.query();
    this.selectCategory = function (selectedCat) {
        this.products = categoryDAO.query({"category": selectedCat});
    };  
    this.returnProducts = function (){
        this.products = productDAO.query();
    };
    this.selectedProduct = $sessionStorage.selectedProduct;

});

module.controller('CustomerController', function (registerDAO, signInDAO, $sessionStorage, $window) {
    this.registerCustomer = function (customer) {
        registerDAO.save(null, customer,
            // success callback
            function () {
                $window.location = 'signin.html';
            },
            // error callback
            function (error) {
                console.log(error);
            }
        );
    };
    this.signInMessage = "Please sign in to continue.";
    
    // alias 'this' so that we can access it inside callback functions
    let ctrl = this;
  
    this.signIn = function (username, passWord) {
        // get customer from web service
        signInDAO.get({'username': username},
        // success
            function (customer) {
            // also store the retrieved customer
                $sessionStorage.customer = customer;
            // redirect to home
                $window.location = 'products.html';
            },
            // fail
            function () {
                ctrl.signInMessage = 'Sign in failed. Please try again.';
            }
        );
    };
    this.checkSignIn = function () {
        // has the customer been added to the session?
        if ($sessionStorage.customer) {
            this.signedIn = true;
            this.welcome = "Welcome " + $sessionStorage.customer.firstName;
        } else {
            this.signedIn = false;
        }
    };
    
    this.signOut = function () {
        delete $sessionStorage.customer;
        this.signedIn = false;
    };
});

module.factory('cart', function ($sessionStorage) {
    let cart = new ShoppingCart();

    // is the cart in the session storage?
    if ($sessionStorage.cart) {

        // reconstruct the cart from the session data
        cart.reconstruct($sessionStorage.cart);
    }

    return cart;
});

