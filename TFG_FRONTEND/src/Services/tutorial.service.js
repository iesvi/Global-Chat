import http from "../http-common";

class TutorialDataService {
  getAll() {
    return http.get("/correo/findAll");
  }

  create(data) {
    return http.post("/correo/createCorreo", data);
  }

  delete(id) {
    return http.delete(`/correo/deleteCorreo/${id}`);
  }

  deleteAll() {
    return http.delete(`/correo/deleteAllCorreos`);
  }

  findByRemitente(remitente) {
    return http.get(`/correo/findByRemitente/${remitente}`);
  }
}

export default new TutorialDataService();