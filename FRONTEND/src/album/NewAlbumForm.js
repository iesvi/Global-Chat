import React, { useState } from "react";
import { app } from "../base";

const db = app.firestore();

export const NewAlbumForm = () => {
  const [albumName, setAlbumName] = useState("");

  const onAlbumNameChange = (e) => {
    setAlbumName(e.target.value);
  };

  const onAlbumCreate = () => {
    if (!albumName) {
      return;
    }
    db.collection("albums").doc(albumName).set({
      name: albumName,
    });
    setAlbumName("");
  };

  return (
    <>
      <div className="center2">
        <button onClick={onAlbumCreate}>Crear Album</button>
      </div>
      <br />
      <div className="center1">
        <input className="input2" placeholder="Nombre Album" value={albumName} onChange={onAlbumNameChange} type="text" />
      </div>
    </>
  );
};
