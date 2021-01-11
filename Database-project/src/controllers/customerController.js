const controller = {};
const myConnection = require("express-myconnection");

controller.list = (req, res) => {
  req.getConnection((err, conn) => {
    conn.query("SELECT * FROM customer", (err, customers) => {
      if (err) {
        res.json(err);
      }
      console.log(customers);
      res.render("customers", {
        data: customers,
      });
    });
  });
};

controller.save = (req, res) => {
  const data = req.body;
  console.log(req.body);
  req.getConnection((err, connection) => {
    const query = connection.query(
      "INSERT INTO customer set ?",
      data,
      (err, customer) => {
        console.log(customer);
        res.redirect("/Clientes");
      }
    );
  });
};

controller.edit = (req, res) => {
  const id = req.params.id;
  req.getConnection((err, conn) => {
    conn.query("SELECT * FROM customer WHERE id = ?", [id], (err, rows) => {
      console.log(rows);
      console.log(rows[0]);
      res.render("customers_edit", {
        data: rows[0],
      });
    });
  });
};

controller.update = (req, res) => {
  const id = req.params.id;
  const newCustomer = req.body;
  req.getConnection((err, conn) => {
    conn.query(
      "UPDATE customer set ? where id = ?",
      [newCustomer, id],
      (err, rows) => {
        res.redirect("/Clientes");
      }
    );
  });
};

controller.delete = (req, res) => {
  const id = req.params.id;
  req.getConnection((err, connection) => {
    connection.query("DELETE FROM customer WHERE id = ?", [id], (err, rows) => {
      res.redirect("/Clientes");
    });
  });
};

module.exports = controller;
