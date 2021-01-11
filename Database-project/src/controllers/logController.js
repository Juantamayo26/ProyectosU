const controller = {};
const myConnection = require("express-myconnection");

controller.list = (req, res) => {
  req.getConnection((err, conn) => {
    conn.query("select * from mysql.general_log", (err, customers) => {
      if (err) {
        res.json(err);
      }
      console.log(customers);
      res.send(customers);
    });
  });
};

module.exports = controller;
