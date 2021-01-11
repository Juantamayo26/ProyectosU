//const router = require('express').Router();
const { Router } = require("express");
const router = new Router();
const customerController = require("../controllers/inventoryController");
const { isLoggedIn } = require('../lib/auth');

router.get("/",customerController.list);
router.post("/add",isLoggedIn, customerController.save);

router.get("/update/:id",isLoggedIn, customerController.edit);
router.post("/update/:id",isLoggedIn, customerController.update);
router.get("/delete/:id", isLoggedIn,customerController.delete);

//id, name , _size, counter, price, status, image
module.exports = router;
