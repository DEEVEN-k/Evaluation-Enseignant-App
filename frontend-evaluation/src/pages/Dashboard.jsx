import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Dashboard = () => {
    const navigate = useNavigate();
    const [user, setUser] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');
        const storedUser = localStorage.getItem('user');

        if (!token || !storedUser) {
            navigate('/login');
        } else {
            setUser(JSON.parse(storedUser));
        }
    }, [navigate]);

    if (!user) return <div className="text-center mt-10">Chargement...</div>;

    return (
        <div className="min-h-screen bg-gray-100 p-6">
            <div className="max-w-4xl mx-auto">
                <h1 className="text-3xl font-bold mb-4">Bienvenue, {user.prenom} {user.nom} 👋</h1>
                <p className="mb-6 text-gray-600">Rôle : <span className="font-semibold">{user.role}</span></p>

                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                    <div className="bg-white p-6 rounded-xl shadow-md">
                        <h2 className="text-xl font-semibold mb-2">Cours</h2>
                        <p className="text-gray-600">Voir les cours disponibles ou assignés.</p>
                    </div>
                    <div className="bg-white p-6 rounded-xl shadow-md">
                        <h2 className="text-xl font-semibold mb-2">Évaluations</h2>
                        <p className="text-gray-600">Accéder aux évaluations et résultats.</p>
                    </div>
                    <div className="bg-white p-6 rounded-xl shadow-md">
                        <h2 className="text-xl font-semibold mb-2">Profil</h2>
                        <p className="text-gray-600">Modifier vos informations personnelles.</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
