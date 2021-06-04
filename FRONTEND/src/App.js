import React, { useState, useEffect } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Signin from "./signin/Signin";
import Signup from "./signup/Signup";
import Profile from "./profile/Profile";
import Chat from "./chat/Chat";
import "./App.css";
import IdleTimerContainer from "./SessionTimeoutComponents/IdleTimerContainer";

import { app } from "./base";
import { Album } from "./album/Album";
import { Home } from "./album/Home";

const db = app.firestore();

export const AppContext = React.createContext();
const App = (props) => {

  const [albums, setAlbums] = useState([]);

  useEffect(() => {
    const unmount = db.collection("albums").onSnapshot((snapshot) => {
      const tempAlbums = [];
      snapshot.forEach((doc) => {
        tempAlbums.push({ ...doc.data(), id: doc.id });
      });
      setAlbums(tempAlbums);
    });
    return unmount;
  }, []);

  return (
    <div className="App" >
      <div>
        <IdleTimerContainer></IdleTimerContainer>
      </div>
      <BrowserRouter>
        <Switch>
          <Route
            exact path="/"
            render={(props) => <Profile {...props} />} />
          <Route
            exact
            path="/login"
            render={(props) => <Signin {...props} />}
          />
          <Route
            exact
            path="/signup"
            render={(props) => <Signup {...props} />}
          />
          <Route exact path="/chat" render={(props) => <Chat {...props} />} />

          <Route exact path="/album" render={() => <Home albums={albums} />} />
          <Route path="/album/:album" component={Album} />

        </Switch>
      </BrowserRouter>
    </div>
  );
};

export default App;
