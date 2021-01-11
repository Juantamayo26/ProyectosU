const router = require('express').Router();

const customerController = require('../controllers/vendorsController');
const { isLoggedIn } = require('../lib/auth');

router.get('/', isLoggedIn, customerController.list);
router.post('/add',isLoggedIn, customerController.save);
router.get('/update/:id',isLoggedIn, customerController.edit);
router.post('/update/:id',isLoggedIn, customerController.update);
router.get('/delete/:id',isLoggedIn, customerController.delete);

module.exports = router;
