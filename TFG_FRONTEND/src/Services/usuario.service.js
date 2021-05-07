import http from "../http-common";

class UsuarioDataService {
    getAll() {
        return http.get("/usuario/findAll");
    }

    findByUsuario(titulo) {
        return http.get(`/usuario/findByUsuario/${titulo}`);
    }

    findByTelefono(telefono) {
        return http.get(`/usuario/findByTelefono/${telefono}`);
    }

    create(data) {
        return http.post("/usuario/createUsuario", data);
    }

    update(id, data) {
        return http.put(`/usuario/updateUsuario/${id}`, data);
    }

    delete(id) {
        return http.delete(`/usuario/deleteUsuario/${id}`);
    }

}

export default new UsuarioDataService();