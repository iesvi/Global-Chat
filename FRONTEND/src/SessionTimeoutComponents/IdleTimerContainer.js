import IdleTimer from 'react-idle-timer'
import React, { useState, useRef } from 'react'
import Modal from 'react-modal'

Modal.setAppElement('#root')

function IdleTimetContainer(props) {
    const [isLoggedIn, setIsLoggedIn] = useState(true)
    const [modalIsOpen, setModalIsOpen] = useState(false)
    const idleTimerRef = useRef(null)
    const sessionTimeoutRef = useRef(null)

    const onIdle = () => {
        console.log("User is idle")
        setModalIsOpen(true)
        sessionTimeoutRef.current = setTimeout(logOut, 30000)
    }

    const stayActive = () => {
        setModalIsOpen(false)
        clearTimeout(sessionTimeoutRef.current)
        console.log("Usuario activo")
    }


    const logOut = () => {
        setModalIsOpen(false)
        setIsLoggedIn(false)
        clearTimeout(sessionTimeoutRef.current)
        localStorage.removeItem("accessToken");
        window.location.href = "/login"
        console.log("El usuario se ha deslogeado")
    }

    return (
        <div>
            {/* {
                isLoggedIn ? <h3>Hola Sam</h3> : <h3>Hola Guest</h3>
            } */}
            <Modal isOpen={modalIsOpen}>
                <h2>¡Has estado inactivo por un tiempo!</h2>
                <p>Por seguridad, seras deslogeado en 30 segundos a no ser que quiera volver a utilizar nuestra app</p>
                <div>
                    <button onClick={stayActive} class="btn btn-success">Mantenerme Conectado</button>⠀
                    <button onClick={logOut} class="btn btn-warning" >Logout</button>
                </div>
            </Modal>
            <IdleTimer ref={idleTimerRef} timeout={120 * 1000} onIdle={onIdle}></IdleTimer>
        </div>
    )
}

export default IdleTimetContainer
