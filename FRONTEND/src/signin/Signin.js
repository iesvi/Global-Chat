import React, { useEffect, useState } from "react";
import { Form, Input, Button, Divider, notification } from "antd";
import {
  UserOutlined,
  LockOutlined,
  FacebookFilled
} from "@ant-design/icons";
import { login, facebookLogin } from "../util/ApiUtil";
import "./Signin.css";
import logo from "../img/logo.png";

/*global FB*/

const Signin = (props) => {
  const [loading, setLoading] = useState(false);
  const [facebookLoading, setFacebookLoading] = useState(false);
  const [test, setTest] = useState(localStorage.getItem("accessToken"));

  useEffect(() => {
    if (localStorage.getItem("accessToken") !== null) {
      props.history.push("/");
    }
    initFacebookLogin();
  }, []);

  useEffect(() => {
    initFacebookLogin();
  }, [test]);

  const initFacebookLogin = () => {
    window.fbAsyncInit = function () {
      FB.init({
        appId: "875279469689923",
        autoLogAppEvents: true,
        xfbml: true,
        version: "v7.0",
      });
    };
  };

  const getFacebookAccessToken = () => {
    setFacebookLoading(true);
    FB.login(
      function (response) {
        if (response.status === "connected") {
          const facebookLoginRequest = {
            accessToken: response.authResponse.accessToken,
          };
          facebookLogin(facebookLoginRequest)
            .then((response) => {
              localStorage.setItem("accessToken", response.accessToken);
              props.history.push("/");
              setFacebookLoading(false);
            })
            .catch((error) => {
              if (error.status === 401) {
                notification.error({
                  message: "Error",
                  description: "Invalid credentials",
                });
              } else {
                notification.error({
                  message: "Error",
                  description:
                    error.message ||
                    "¡Lo siento! A ocurrido un error. Por favor, inténtelo de nuevo!",
                });
              }
              setFacebookLoading(false);
            });
        } else {
          console.log(response);
        }
      },
      { scope: "email" }
    );
  };

  const onFinish = (values) => {
    setLoading(true);
    login(values)
      .then((response) => {
        localStorage.setItem("accessToken", response.accessToken);
        props.history.push("/");
        setLoading(false);
      })
      .catch((error) => {
        if (error.status === 401) {
          notification.error({
            message: "Error",
            description: "El usuario o la contraseña son incorrectos. ¡Por favor, inténtelo de nuevo!",
          });
        } else {
          notification.error({
            message: "Error",
            description:
              error.message || "¡Lo siento! A ocurrido un error. Por favor, inténtelo de nuevo!",
          });
        }
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
          name="username"
          rules={[{ required: true, message: "Por favor, introduce tu usuario!" }]}
        >
          <Input
            size="large"
            prefix={<UserOutlined className="site-form-item-icon" />}
            placeholder="Usuario"
          />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[{ required: true, message: "¡Por favor, introduce tu contraseña!" }]}
        >
          <Input
            size="large"
            prefix={<LockOutlined className="site-form-item-icon" />}
            type="password"
            placeholder="Contraseña"
          />
        </Form.Item>
        <Form.Item>
          <Button
            shape="round"
            size="large"
            htmlType="submit"
            className="login-form-button"
            loading={loading}
          >
            Iniciar Sesión
          </Button>
        </Form.Item>
        <Divider></Divider>
        <Form.Item>
          <Button
            icon={<FacebookFilled style={{ fontSize: 20 }} />}
            loading={facebookLoading}
            className="login-with-facebook"
            shape="round"
            size="large"
            onClick={getFacebookAccessToken}
          >
            Iniciar Sesión con Facebook
          </Button>
        </Form.Item>
        ¿No tienes cuenta? <a href="/signup">Regístrate</a>
      </Form>
    </div>
  );
};

export default Signin;
