import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

// Components
import Header from "./components/Header";
import Footer from "./components/Footer";

// Pages
import Home from "./pages/Home";
import Register from "./pages/Register";
import Login from "./pages/Login";
// Pages

function App() {
  return (
    <>
    
      <Router>
        <Header />
        <Switch>
          <Route exact path="/">
            <Home />
          </Route>          
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
        </Switch>
        <Footer />
      </Router>
    </>
  );
}

export default App;
