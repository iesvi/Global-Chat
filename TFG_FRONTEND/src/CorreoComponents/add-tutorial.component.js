import React, { Component } from "react";
import TutorialDataService from "../Services/tutorial.service";
import { Link } from "react-router-dom";
import NotificacionComponent from './NotificacionComponent';
import Login from '../Login/components/Header';
import firebase from 'firebase';
import Checkbox from '@material-ui/core/Checkbox';

export default class AddTutorial extends Component {
  constructor(props) {
    super(props);
    this.onChangeRemitente = this.onChangeRemitente.bind(this);
    this.onChangeDestinatario = this.onChangeDestinatario.bind(this);
    this.onChangeAsunto = this.onChangeAsunto.bind(this);
    this.onChangeMensaje = this.onChangeMensaje.bind(this);
    this.saveTutorial = this.saveTutorial.bind(this);
    this.newTutorial = this.newTutorial.bind(this);

    this.state = {
      id: null,
      remitente: "",
      destinatario: "",
      asunto: "",
      mensaje: "",
      user: null
    };
  }

  componentDidMount() {
    firebase.auth().onAuthStateChanged(user => {
      if (user) {
        this.setState({ user: user })
      } else {
        this.setState({ user: null })
      }
    })
  }

  handleAuth() {
    const provider = new firebase.auth.GoogleAuthProvider()

    const logeado = () => {
      window.location.href = "/bandejaEntrada"
    }

    firebase.auth().signInWithPopup(provider)
      .then(logeado)
      .catch(error => console.error(`Error: ${error.code}: ${error.message}`))
  }

  handleLogout() {

    const noLogeado = () => {
      window.location.href = "/"
    }

    firebase.auth().signOut()
      .then(noLogeado)
      .catch(error => console.error(`Error: ${error.code}: ${error.message}`))
  }

  onChangeRemitente(e) {
    this.setState({
      remitente: e.target.value
    });
  }

  onChangeDestinatario(e) {
    this.setState({
      destinatario: e.target.value
    });
  }

  onChangeAsunto(e) {
    this.setState({
      asunto: e.target.value
    });
  }

  onChangeMensaje(e) {
    this.setState({
      mensaje: e.target.value
    });
  }

  saveTutorial() {
    var data = {
      remitente: this.state.remitente,
      destinatario: this.state.destinatario,
      asunto: this.state.asunto,
      mensaje: this.state.mensaje
    };

    TutorialDataService.create(data)
      .then(response => {
        this.setState({
          id: response.data.id,
          remitente: response.data.remitente,
          destinatario: response.data.destinatario,
          asunto: response.data.asunto,
          mensaje: response.data.mensaje
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  newTutorial() {
    this.setState({
      id: null,
      remitente: "",
      destinatario: "",
      asunto: "",
      mensaje: ""
    });
  }

  volver() {
    window.location.href = "/bandejaEntrada"
  }

  render() {
    return (
      <div>

        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/bandejaEntrada"} className="navbar-brand">
            Global-Mail
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <button class="btn btn-danger" onClick={this.volver}>
                Volver
              </button>
            </li>
          </div>
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Login
                user={this.state.user}
                onAuth={this.handleAuth.bind(this)}
                onLogout={this.handleLogout.bind(this)}
              />
            </li>
          </div>
        </nav>

        <div>
          <NotificacionComponent />
        </div>

        <br />

        <div className="submit-form">
          {this.state.submitted ? (
            <div>
              <h4>¡El correo se ha mandado con éxito!</h4>
            </div>
          ) : (
            <div>

              <div className="form-group">
                <label htmlFor="remitente">Remitente</label>
                <input
                  type="text"
                  className="form-control"
                  id="remitente"
                  required
                  value={this.state.remitente}
                  onChange={this.onChangeRemitente}
                  name="remitente"
                />
              </div>

              <div className="form-group">
                <label htmlFor="destinatario">Destinatario</label>
                <input
                  type="text"
                  className="form-control"
                  id="destinatario"
                  required
                  value={this.state.destinatario}
                  onChange={this.onChangeDestinatario}
                  name="destinatario"
                />
              </div>

              <div className="form-group">
                <label htmlFor="asunto">Asunto</label>
                <input
                  type="text"
                  className="form-control"
                  id="asunto"
                  required
                  value={this.state.asunto}
                  onChange={this.onChangeAsunto}
                  name="asunto"
                />
              </div>

              <div className="form-group">
                <label htmlFor="mensaje">Mensaje</label>
                <textarea
                  type="text"
                  className="form-control"
                  id="mensaje"
                  required
                  value={this.state.mensaje}
                  onChange={this.onChangeMensaje}
                  name="mensaje"
                />
              </div>

              <button onClick={this.saveTutorial} className="btn btn-success">
                Enviar
              </button>

              <div>
                <Checkbox
                  color="primary"
                  inputProps={{ 'aria-label': 'secondary checkbox' }}
                /> <strong>Cifrar</strong>
                <Checkbox
                  color="primary"
                  inputProps={{ 'aria-label': 'secondary checkbox' }}
                /> <strong>Firmar</strong>
              </div>
              <div>
                <Checkbox
                  color="primary"
                  inputProps={{ 'aria-label': 'secondary checkbox' }}
                /> <strong>Autodestruir</strong>
              </div>
            </div>
          )
          }
        </div>
      </div >
    );
  }
}
