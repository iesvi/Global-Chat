import React, { Component } from "react";
import TutorialDataService from "../Services/tutorial.service";
import { Link } from "react-router-dom";
import NotificacionComponent from './NotificacionComponent';

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
      mensaje: ""
    };
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

  render() {
    return (
      <div>

        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/bandejaEntrada"} className="navbar-brand">
            Global-Mail
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/enviarCorreo"} className="nav-link">
                Enviar Correo
              </Link>
            </li>
          </div>
          <div className="navbar-nav">
            <li className="nav-item">
              <Link to={"/"} className="nav-link">
                Logout
              </Link>
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
                <input
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
            </div>
          )
          }
        </div>
      </div >
    );
  }
}
