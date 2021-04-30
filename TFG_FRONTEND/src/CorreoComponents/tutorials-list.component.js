import React, { Component } from "react";
import TutorialDataService from "../Services/tutorial.service";
import { Link } from "react-router-dom";

export default class TutorialsList extends Component {
  constructor(props) {
    super(props);
    this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
    this.retrieveTutorials = this.retrieveTutorials.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveTutorial = this.setActiveTutorial.bind(this);
    this.removeAllTutorials = this.removeAllTutorials.bind(this);
    this.deleteTutorial = this.deleteTutorial.bind(this);
    this.searchTitle = this.searchTitle.bind(this);

    this.state = {
      tutorials: [],
      currentTutorial: null,
      currentIndex: -1,
      searchTitle: ""
    };
  }

  componentDidMount() {
    this.retrieveTutorials();
  }

  onChangeSearchTitle(e) {
    const searchTitle = e.target.value;

    this.setState({
      searchTitle: searchTitle
    });
  }

  retrieveTutorials() {
    TutorialDataService.getAll()
      .then(response => {
        this.setState({
          tutorials: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveTutorials();
    this.setState({
      currentTutorial: null,
      currentIndex: -1
    });
  }

  setActiveTutorial(tutorial, index) {
    this.setState({
      currentTutorial: tutorial,
      currentIndex: index
    });
  }

  deleteTutorial() {
    TutorialDataService.delete(this.state.currentTutorial.id)
      .then(response => {
        console.log(response.data);
        this.refreshList();
      })
      .catch(e => {
        console.log(e);
      });
  }

  removeAllTutorials() {
    TutorialDataService.deleteAll()
      .then(response => {
        console.log(response.data);
        this.refreshList();
      })
      .catch(e => {
        console.log(e);
      });
  }

  searchTitle() {
    TutorialDataService.findByRemitente(this.state.searchTitle)
      .then(response => {
        this.setState({
          tutorials: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    const { searchTitle, tutorials, currentTutorial, currentIndex } = this.state;

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

        <br />

        <div className="list row">
          <div className="col-md-8">
            <div className="input-group mb-3">

              <input
                type="text"
                className="form-control"
                placeholder="Busca por usuario"
                value={searchTitle}
                onChange={this.onChangeSearchTitle}
              />

              <div className="input-group-append">
                <button
                  className="btn btn-outline-secondary"
                  type="button"
                  onClick={this.searchTitle}
                >
                  Buscar
              </button>
              </div>
            </div>
          </div>
          <div className="col-md-6">
            <h4>Bandeja de Entrada</h4>

            <ul className="list-group">
              {tutorials &&
                tutorials.map((tutorial, index) => (
                  <li
                    className={
                      "list-group-item " +
                      (index === currentIndex ? "active" : "")
                    }
                    onClick={() => this.setActiveTutorial(tutorial, index)}
                    key={index}
                  >
                    <strong>{tutorial.remitente}</strong>
                  </li>
                ))}
            </ul>

            <br />

            <button
              class="btn btn-danger"
              onClick={this.removeAllTutorials}
            >
              Borrar Todos
          </button>

          </div>
          <div className="col-md-6">
            {currentTutorial ? (
              <div>

                <h4>Mensajes</h4>

                <div>
                  <label>
                    <strong>Asunto:</strong>
                  </label>{" "}
                  {currentTutorial.asunto}
                </div>

                <div>
                  <label>
                    <strong>Mensaje:</strong>
                  </label>{" "}
                  {currentTutorial.mensaje}
                </div>

                <button
                  className="badge badge-danger mr-2"
                  onClick={this.deleteTutorial}
                >
                  Eliminar
               </button>

              </div>
            ) : (
              <div>
                <br />
                <p>Por favor, haz click en un correo...</p>
              </div>
            )}
          </div>
        </div>
      </div >
    );
  }
}
