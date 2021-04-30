import http from "../http-common";

class UsuarioDataService {
    getAll() {
        return http.get("/usuario/findAll");
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


    findByUsuario(titulo) {
        return http.get(`/usuario/findByUsuario/${titulo}`);
    }
}

export default new UsuarioDataService();