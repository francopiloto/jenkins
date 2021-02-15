const express = require("express");
const path = require("path");
const app = express();

const port = process.argv[2] || 3000;

app.use(express.static(path.join(__dirname, "../dist")));

app.get("/*", function (_req, res) {
  res.sendFile(path.join(__dirname, "../dist", "index.html"));
});

app.listen(port);
console.log(`RUNNING ON PORT ${port}`);
