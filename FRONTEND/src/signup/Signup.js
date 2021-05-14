import React, { useEffect, useState } from "react";
import { Form, Input, Button, notification } from "antd";
import { signup } from "../util/ApiUtil";
import "./Signup.css";
import logo from "../img/logo.png";

const Signup = (props) => {
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (localStorage.getItem("accessToken") !== null) {
      props.history.push("/");
    }
  }, []);

  const onFinish = (values) => {
    setLoading(true);
    signup(values)
      .then((response) => {
        notification.success({
          message: "Success",
          description:
            "¡Gracias! Te has registrado con éxito. ¡Por favor, inicie sesión para continuar!",
        });
        props.history.push("/login");
        setLoading(false);
      })
      .catch((error) => {
        notification.error({
          message: "Error",
          description:
            error.message || "¡Lo siento! Algo ha ido mal. ¡Por favor, inténtelo de nuevo!",
        });
        setLoading(false);
      });
  };

  return (
    <div className="login-container">
      <img src={logo} alt="logo" width="190" />
      <Form
        name="normal_login"
        className="login-form"
        initialValues={{ remember: true }}
        onFinish={onFinish}
      >
        <Form.Item
          name="name"
          rules={[{ required: true, message: "¡Por favor, introduce tu nombre!" }]}
        >
          <Input size="large" placeholder="Nombre" />
        </Form.Item>
        <Form.Item
          name="username"
          rules={[{ required: true, message: "¡Por favor, introduce tu nombre de usuario!" }]}
        >
          <Input size="large" placeholder="Usuario" />
        </Form.Item>
        <Form.Item
          name="email"
          rules={[{ required: true, message: "¡Por favor, introduce tu email!" }]}
        >
          <Input size="large" placeholder="Email" />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[{ required: true, message: "¡Por favor, introduce tu contraseña!" }]}
        >
          <Input size="large" type="password" placeholder="Contraseña" />
        </Form.Item>
        <Form.Item
          name="profilePicUrl"
          rules={[
            {
              required: true,
              message: "¡Por favor, introduce la URL de la foto de perfil!",
            },
          ]}
        >
          <Input size="large" placeholder="URL de la foto de perfil" />
        </Form.Item>
        <Form.Item>
          <Button
            shape="round"
            size="large"
            htmlType="submit"
            className="login-form-button"
            loading={loading}
          >
            Registrarse
          </Button>
        </Form.Item>
        ¿Ya estás registrado? <a href="/login">Iniciar Sesión</a>
      </Form>
    </div>
  );
};

export default Signup;
