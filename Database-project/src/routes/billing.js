const router = require("express").Router();

const billingController = require("../controllers/billingController");
const { isLoggedIn } = require('../lib/auth');
 
router.get("/",isLoggedIn, billingController.list);
router.get('/:id/u',isLoggedIn, billingController.inventory);
router.get('/:id1/:id2',isLoggedIn, billingController.update1, billingController.update2, billingController.update3, billingController.id1, billingController.id2, billingController.payment);

module.exports = router;
