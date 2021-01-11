const controller = {};
const myConnection = require("express-myconnection");

controller.list = (req, res) => {
  req.getConnection((err, conn) => {
    conn.query("SELECT * FROM vendors", (err, vendors) => {
      if (err) {
        res.json(err);
      }
      console.log(vendors);
      res.render("vendors", {
        data: vendors,
      });
    });
  });
};

controller.save = (req, res) => {
  const data = req.body;
  console.log(req.body);
  req.getConnection((err, connection) => {
    const query = connection.query(
      "INSERT INTO vendors set ?",
      data,
      (err, customer) => {
        res.redirect("/Proovedores");
      }
    );
  });
};

controller.edit = (req, res) => {
  const id = req.params.id;
  req.getConnection((err, conn) => {
    conn.query("SELECT * FROM vendors WHERE id = ?", [id], (err, rows) => {
      console.log(rows);
      console.log(rows[0]);
      res.render("vendors_edit", {
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
      "UPDATE vendors set ? where id = ?",
      [newCustomer, id],
      (err, rows) => {
        res.redirect("/Proovedores");
      }
    );
  });
};

controller.delete = (req, res) => {
  const id = req.params.id;
  req.getConnection((err, connection) => {
    connection.query("DELETE FROM vendors WHERE id = ?", [id], (err, rows) => {
      res.redirect("/Proovedores");
    });
  });
};

module.exports = controller;
