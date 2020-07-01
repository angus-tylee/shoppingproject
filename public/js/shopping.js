/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global $window */

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

module.factory('cart', function ($sessionStorage) {
    let cart = new ShoppingCart();

    // is the cart in the session storage?
    if ($sessionStorage.cart) {

        // reconstruct the cart from the session data
        cart.reconstruct($sessionStorage.cart);
    }

    return cart;
});

module.factory('categoryDAO', function ($resource) {
    return $resource('/api/categories/:cat');
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

module.controller('ShoppingCartController', function (cart, $sessionStorage, saleDAO, $window) {
    this.items = cart.getItems();
    this.total = cart.getTotal();
    this.selectedProduct = $sessionStorage.selectedProduct;

    let ctrl = this;

    this.buyProduct = function (product) {
        $sessionStorage.selectedProduct = product;
        $window.location.href = 'add-to-cart.html';
    };

    this.addToCart = function (quantity) {
        
        let prod = $sessionStorage.selectedProduct;
        let newItem = new SaleItem(prod, quantity);
        cart.addItem(newItem);
        $sessionStorage.cart = cart;
        $window.location.href = '/cart.html';
    };
    
    this.checkOutCart = function (){
        
        let cust = $sessionStorage.customer;
        
        cart.setCustomer(cust);

        saleDAO.save(cart);
        
        delete $sessionStorage.cart;
        $window.location.href = '/thank-you.html';
        
    };
});


module.controller('ProductController', function (productDAO, categoryDAO) {
    // load the products
    this.products = productDAO.query();
    // load the categories
    this.categories = categoryDAO.query();
    // click handler for the category filter buttons
    this.selectCategory = function (selectedCat) {
        this.products = categoryDAO.query({"cat": selectedCat});
        this.displayProducts = function () {
            this.products = productDAO.query();
        };
    };
});
module.controller('CustomerController', function (registerDAO, signInDAO, $sessionStorage, $window) {
    this.signInMessage = "Please sign in to continue.";

    // alias 'this' so that we can access it inside callback functions
    let ctrl = this;
    this.registerCustomer = function (customer) {
        registerDAO.save(null, customer,
                // success callback
                        function () {
                            $window.location = 'sign-in.html';
                        },
                        // error callback
                                function (error) {
                                    console.log(error);
                                }
                        );
                    };
            this.signIn = function (username, password) {
                // get customer from web service
                signInDAO.get({'username': username},
                        // success
                                function (customer) {
                                    // also store the retrieved customer
                                    $sessionStorage.customer = customer;
                                    // redirect to home
                                    $window.location.href = '.';
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
                        $sessionStorage.$reset();
                        this.signedIn = false;
                        $window.location.href = '.';
                    };
                });




