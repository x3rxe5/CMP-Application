import React, { useEffect } from "react";
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
import { useDispatch} from "react-redux";
import axios from "axios";
import { isAuth } from "./slices/AuthSlices";
import ProtectedRoute from "./components/ProtectedRoute";
import ChatRoom from "./pages/ChatRoom";
// Pages

function App() {
  
  const dispatch = useDispatch();
  useEffect(() => {
      axios.get("http://localhost:8080/validate-cookie",{ withCredentials:true })
      .then(res => {
          if(res.data === 1){
            dispatch(isAuth());
          }
      })
      .catch(err => console.log(err));
  },[dispatch]);

  return (
    <>        
      <Router>
        <Header />
          <Route exact path="/">
            <Home />
          </Route>          
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
        <Switch>
          <ProtectedRoute exact path="/chatroom" component={ChatRoom} />
        </Switch>
        <Footer />
      </Router>
    </>
  );
}

export default App;
