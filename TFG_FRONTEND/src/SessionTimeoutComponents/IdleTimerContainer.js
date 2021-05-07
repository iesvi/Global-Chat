import IdleTimer from 'react-idle-timer'
import React, { useState, useRef } from 'react'
import Modal from 'react-modal'
import App from '../App'
import TutorialsList from '../CorreoComponents/tutorials-list.component'

Modal.setAppElement('#root')

function IdleTimetContainer() {
    const [isLoggedIn, setIsLoggedIn] = useState(true)
    const [modalIsOpen, setModalIsOpen] = useState(false)
    const idleTimerRef = useRef(null)
    const sessionTimeoutRef = useRef(null)

    const onIdle = () => {
        console.log("User is idle")
        setModalIsOpen(true)
        sessionTimeoutRef.current = setTimeout(logOut, 10000)
    }

    const stayActive = () => {
        setModalIsOpen(false)
        clearTimeout(sessionTimeoutRef.current)
        console.log("User is active")
    }

    const logOut = () => {
        const log = new TutorialsList();
        log.handleLogout();
        setModalIsOpen(false)
        setIsLoggedIn(false)
        clearTimeout(sessionTimeoutRef.current)
        window.location.href = "/"
        console.log("User has logged out")
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
            <IdleTimer ref={idleTimerRef} timeout={50000 * 1000} onIdle={onIdle}></IdleTimer>
        </div>
    )
}

export default IdleTimetContainer
