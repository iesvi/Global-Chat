import React, { useState } from 'react';
import './CreateUser.css';
import topImg from '../assets/images/appBackground.jpg';
import Input from '../Login/components/Input';
import Item from '../Login/components/Item';
import Title from '../Login/components/Title';
import ErrorNotification from '../commons/ErrorNotification';
import Button from '../commons/RegularButton';
import { Redirect, Link } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles';
import Backdrop from '@material-ui/core/Backdrop';
import CircularProgress from '@material-ui/core/CircularProgress';
import UsuarioDataService from "../Services/usuario.service";

const useStyles = makeStyles(theme => ({
    backdrop: {
        zIndex: 2,
        color: '#fff',
    },
}));

const CreateUser = () => {

    const classes = useStyles();

    const [username, setUsername] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [password, setPassword] = useState('');
    const [passwordAgain, setPasswordAgain] = useState('');

    const [isLoading, setIsLoading] = useState(false);
    const [created, setCreated] = useState(false)

    const [errors, setErrors] = useState({
        usernameError: false,
        firstNameError: false,
        lastNameError: false,
        passwordError: false,
        passwordAgainError: false
    })

    function handleChange(name, value) {
        switch (name) {
            case 'username':
                if (value < 1) {
                    setErrors({ ...errors, usernameError: true })
                } else {
                    setErrors({ ...errors, usernameError: false })
                    setUsername(value)
                }
                break;
            case 'firstName':
                if (value < 1) {
                    setErrors({ ...errors, firstNameError: true })
                } else {
                    setErrors({ ...errors, firstNameError: false })
                    setFirstName(value)
                }
                break;
            case 'lastName':
                if (value < 1) {
                    setErrors({ ...errors, lastNameError: true })
                } else {
                    setErrors({ ...errors, lastNameError: false })
                    setLastName(value)
                }
                break;
            case 'password':
                if (value < 1) {
                    setErrors({ ...errors, passwordError: true })
                } else {
                    setErrors({ ...errors, passwordError: false })
                    setPassword(value)
                }
                break;
            case 'passwordAgain':
                if (password.length < 6) {
                    setErrors({ ...errors, passwordError: true })
                } else if (password === value) {
                    setErrors({
                        ...errors, passwordError: false,
                        passwordAgainError: false
                    })
                    setPasswordAgain(value)
                } else {
                    setErrors({
                        ...errors, passwordError: false,
                        passwordAgainError: true
                    })
                }
                break;
            default:
                console.log('no hay valores.')
        }
    }

    let params =
        errors.usernameError === false &&
        errors.firstNameError === false &&
        errors.lastNameError === false &&
        errors.passwordError === false &&
        errors.passwordAgainError === false &&
        username.length > 1 &&
        firstName.length > 1 &&
        lastName.length > 1 &&
        password.length > 5 &&
        password === passwordAgain
        ;

    function handleSubmit() {
        saveUsuario();
        setIsLoading(true)
        let account = { username, firstName, lastName, password }
        if (account) {
            let ac = JSON.stringify(account)
            localStorage.setItem('account', ac)
            setTimeout(() => setCreated(true), 2000)
        }
    }

    function saveUsuario() {
        var data = {
            nombre: username + "@globalmail.com",
            usuario: firstName,
            telefono: lastName,
            contraseña: password
        };

        UsuarioDataService.create(data)
            .then(response => {
                this.setState({
                    id: response.data.id,
                    nombre: response.data.nombre,
                    usuario: response.data.usuario,
                    telefono: response.data.telefono,
                    contraseña: response.data.contraseña,
                    mensaje: response.data.mensaje,
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    let open = true;

    let screenWidth = window.innerWidth;

    return (
        <>

            { created && <Redirect to='/bandejaEntrada' />}

            <div className='CreateUserContainer'>
                <img className='CreateUserImg' src={topImg} alt='' />
                <div className='BackgroundWeb' />

                {screenWidth < 1030 && <Title text='Regístrate' />}

                <div className='createUserContent'>

                    <div className='formCreateUser'>

                        {screenWidth > 1030 && <Title text='Regístrate' />}

                        <Item text='Nombre de usuario' />
                        <Input
                            attribute={{
                                name: 'username',
                                inputType: 'text',
                                ph: ''
                            }}
                            handleChange={handleChange}
                            param={errors.usernameError}
                        />
                        {errors.usernameError &&
                            <ErrorNotification text='Introduzca un nombre de usuario.' />}

                        <Item text='Nombre' />
                        <Input
                            attribute={{
                                name: 'firstName',
                                inputType: 'text',
                                ph: ''
                            }}
                            handleChange={handleChange}
                            param={errors.firstNameError}
                        />
                        {errors.firstNameError &&
                            <ErrorNotification text='Introduzca su nombre.' />}

                        <Item text='Teléfono' />
                        <Input
                            attribute={{
                                name: 'lastName',
                                inputType: 'number',
                                ph: ''
                            }}
                            handleChange={handleChange}
                            param={errors.lastNameError}
                        />
                        {errors.lastNameError &&
                            <ErrorNotification text='Introduzca su teléfono.' />}

                        <Item text='Contraseña' />
                        <Input
                            attribute={{
                                name: 'password',
                                inputType: 'password',
                                ph: ''
                            }}
                            handleChange={handleChange}
                            param={errors.passwordError}
                        />
                        {errors.passwordError &&
                            <ErrorNotification text='Son mínimo 6 carácteres.' />}

                        <Item text='Confirmar Contraseña' />
                        <Input
                            attribute={{
                                name: 'passwordAgain',
                                inputType: 'password',
                                ph: ''
                            }}
                            handleChange={handleChange}
                            param={errors.passwordAgainError}
                        />
                        {errors.passwordAgainError &&
                            <ErrorNotification text="Las contraseñas no coinciden." />}

                        <Button
                            text='Registrar'
                            handleOnClick={handleSubmit}
                            param={params}
                        />

                        <div
                            style={{
                                display: 'flex',
                                justifyContent: 'center',
                                alignItems: 'center'
                            }}>
                            <Link
                                to='/'
                                style={{ color: '#734488' }}
                            >
                                <Item
                                    text='Volver' />
                            </Link>
                        </div>
                    </div>

                    {isLoading &&
                        <Backdrop open={open} className={classes.backdrop}>
                            <CircularProgress color="inherit" />
                        </Backdrop>
                    }

                </div>
            </div>
        </>
    )
};

export default CreateUser;