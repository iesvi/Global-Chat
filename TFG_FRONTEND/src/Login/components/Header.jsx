import React from 'react';

function out() {
    window.location.href = "/";
}

function Header({ user, onAuth, onLogout }) {

    function renderUserData() {
        return (
            <div className='navbar left'>
                <div>
                    <img
                        src={user.photoURL}
                        width='50'
                        alt="Imagen Usuario"
                        className='avatar circle responsive-img' />
                </div>
                <div className="nav-link">{user.displayName}</div>
                <div className="navbar-nav">
                    <button
                        class="btn btn-primary"
                        onClick={onLogout}
                    >
                        Logout
                    </button>
                </div>
            </div>
        )
    }

    function renderLoginButton() {
        return (
            <div className='right'>
                <div>
                    <button
                        class="btn btn-primary"
                        onClick={out}
                    >
                        Logout
                    </button>
                </div>
            </div>
        )
    }

    return (
        <div>
            {user ? renderUserData() : renderLoginButton()}
        </div>
    )

}

export default Header