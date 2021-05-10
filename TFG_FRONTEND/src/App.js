import React, { Component } from "react";
import { Switch, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import AddTutorial from "./CorreoComponents/add-tutorial.component";
import Login from "./Login/Login"
import CreateUser from "./CreateUser/CreateUser";
import TutorialsList from "./CorreoComponents/tutorials-list.component"
import RecuperarContraseña from './RecuperarContraseñaComponents/RecuperarContraseña'
import IdleTimerContainer from './SessionTimeoutComponents/IdleTimerContainer'

class App extends Component {
  render() {
    return (
      <div>
        <div>
          <IdleTimerContainer></IdleTimerContainer>
        </div>
        <div>
          <Switch>
            <Route exact path={["/"]} component={Login} />
            <Route exact path={["/", "/bandejaEntrada"]} component={TutorialsList} />
            <Route exact path={["/", "/enviarCorreo"]} component={AddTutorial} />
            <Route exact path={["/", "/createUser"]} component={CreateUser} />
            <Route exact path={["/", "/recuperarContraseña"]} component={RecuperarContraseña} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
