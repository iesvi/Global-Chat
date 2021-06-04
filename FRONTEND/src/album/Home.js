import React from 'react'
import { Link } from 'react-router-dom'
import { NewAlbumForm } from './NewAlbumForm'

export const Home = ({ albums }) => {

  const back = () => {
    window.location.href = "/"
  }

  return <>
    <section>
      <header>
        <h1 style={{ fontSize: "x-large" }}>Ir a la <Link to="/">Página de Perfil</Link></h1>
        <footer>
          <NewAlbumForm />
        </footer>
      </header>

      {albums.map((album) => (
        <Link to={`/album/${album.id}`}>
          <aside key={album.name}>
            <img src={album.images ? album.images[0].url : ""} alt="¡Introduce alguna imagen!" />
            <h1 style={{ color: '#23CAD5', fontSize: "x-large" }}>{album.name}</h1>
          </aside>
        </Link>
      ))}
    </section>
  </>
}