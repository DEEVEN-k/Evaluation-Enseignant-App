// src/App.js
import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';

import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';
import Evaluation from './pages/Evaluation';
import Classement from './pages/Classement';

function App() {
    const [utilisateur, setUtilisateur] = useState(null);

    useEffect(() => {
        const userData = localStorage.getItem('utilisateur');
        if (userData) {
            setUtilisateur(JSON.parse(userData));
        }
    }, []);

    const isLoggedIn = utilisateur !== null;

    return (
        <Router>
            <Routes>
                {/* Page d'accueil : redirige selon rôle */}
                <Route
                    path="/"
                    element={
                        isLoggedIn ? (
                            utilisateur.role === 'admin' ? (
                                <Navigate to="/dashboard" />
                            ) : (
                                <Navigate to="/evaluation" />
                            )
                        ) : (
                            <Login />
                        )
                    }
                />

                {/* Inscription ouverte à tous */}
                <Route path="/register" element={<Register />} />

                {/* Dashboard pour ADMIN uniquement */}
                <Route
                    path="/dashboard"
                    element={
                        isLoggedIn && utilisateur.role === 'admin' ? (
                            <Dashboard />
                        ) : (
                            <Navigate to="/" />
                        )
                    }
                />

                {/* Évaluation pour tous les utilisateurs connectés */}
                <Route
                    path="/evaluation"
                    element={isLoggedIn ? <Evaluation /> : <Navigate to="/" />}
                />

                {/* Classement visible uniquement pour les étudiants */}
                <Route
                    path="/classement"
                    element={
                        isLoggedIn && utilisateur.role === 'etudiant' ? (
                            <Classement />
                        ) : (
                            <Navigate to="/" />
                        )
                    }
                />

                {/* Fallback */}
                <Route path="*" element={<Navigate to="/" />} />
            </Routes>
        </Router>
    );
}

export default App;
