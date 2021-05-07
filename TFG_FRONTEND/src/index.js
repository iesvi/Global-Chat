import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter } from "react-router-dom";

import App from "./App";
import * as serviceWorker from "./serviceWorker";
import firebase from 'firebase'

firebase.initializeApp({
  apiKey: "AIzaSyDMe6sBmPBT18YApm-OoAJ7m7nTIzJqS_w",
  authDomain: "autenticaciontest-e4f2c.firebaseapp.com",
  databaseURL: "https://autenticaciontest-e4f2c-default-rtdb.firebaseio.com",
  projectId: "autenticaciontest-e4f2c",
  storageBucket: "autenticaciontest-e4f2c.appspot.com",
  messagingSenderId: "809333208214",
  appId: "1:809333208214:web:dc861bc86fa1d95cd83eb7",
  measurementId: "G-PQFQCCXR24"
})

ReactDOM.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>,
  document.getElementById("root")
);

serviceWorker.unregister();
