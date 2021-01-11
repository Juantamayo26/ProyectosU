const router = require("express").Router();

const customerController = require("../controllers/mainController");
const { isLoggedIn } = require('../lib/auth');

router.get("/",isLoggedIn, customerController.disp, customerController.encami, customerController.deben, customerController.c, customerController.p , customerController.list);

//router.get('/', (req, res) => {
//  res.render('main');
//});

module.exports = router;
