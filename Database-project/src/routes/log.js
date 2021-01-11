const router = require("express").Router();

const logController = require("../controllers/logController");
const { isLoggedIn } = require('../lib/auth');

router.get("/",isLoggedIn, logController.list);

module.exports = router;
