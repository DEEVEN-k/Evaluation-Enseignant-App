import React, { useState } from 'react';
import {
    Box, Button, FormControl, FormLabel, Input, Heading, Select, useToast
} from '@chakra-ui/react';
import axios from 'axios';

const Register = () => {
    const [form, setForm] = useState({
        nom: '',
        prenom: '',
        email: '',
        motDePasse: '',
        role: 'etudiant',
        matricule: '',
        classe: '',
        niveau: '',
        matricule_enseignant: '',
        specialite: ''
    });

    const toast = useToast();

    const handleChange = e => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async e => {
        e.preventDefault();

        try {
            await axios.post('http://localhost:8081/api/auth/register', form);
            toast({
                title: 'Inscription réussie.',
                status: 'success',
                duration: 3000,
                isClosable: true
            });
            setForm({
                nom: '',
                prenom: '',
                email: '',
                motDePasse: '',
                role: 'etudiant',
                matricule: '',
                classe: '',
                niveau: '',
                matricule_enseignant: '',
                specialite: ''
            });
        } catch (error) {
            toast({
                title: 'Erreur lors de l’inscription',
                description: error.response?.data?.message || error.message,
                status: 'error',
                duration: 3000,
                isClosable: true
            });
        }
    };

    return (
        <Box maxW="md" mx="auto" mt={10} p={6} borderWidth="1px" borderRadius="lg">
            <Heading mb={6} size="lg" textAlign="center">Inscription</Heading>
            <form onSubmit={handleSubmit}>
                <FormControl isRequired mb={4}>
                    <FormLabel>Nom</FormLabel>
                    <Input name="nom" value={form.nom} onChange={handleChange} />
                </FormControl>

                <FormControl isRequired mb={4}>
                    <FormLabel>Prénom</FormLabel>
                    <Input name="prenom" value={form.prenom} onChange={handleChange} />
                </FormControl>

                <FormControl isRequired mb={4}>
                    <FormLabel>Email</FormLabel>
                    <Input type="email" name="email" value={form.email} onChange={handleChange} />
                </FormControl>

                <FormControl isRequired mb={4}>
                    <FormLabel>Mot de passe</FormLabel>
                    <Input type="password" name="motDePasse" value={form.motDePasse} onChange={handleChange} />
                </FormControl>

                <FormControl isRequired mb={4}>
                    <FormLabel>Rôle</FormLabel>
                    <Select name="role" value={form.role} onChange={handleChange}>
                        <option value="etudiant">Étudiant</option>
                        <option value="enseignant">Enseignant</option>
                    </Select>
                </FormControl>

                {form.role === 'etudiant' && (
                    <>
                        <FormControl isRequired mb={4}>
                            <FormLabel>Matricule</FormLabel>
                            <Input name="matricule" value={form.matricule} onChange={handleChange} />
                        </FormControl>

                        <FormControl isRequired mb={4}>
                            <FormLabel>Classe</FormLabel>
                            <Input name="classe" value={form.classe} onChange={handleChange} />
                        </FormControl>

                        <FormControl isRequired mb={4}>
                            <FormLabel>Niveau</FormLabel>
                            <Input name="niveau" value={form.niveau} onChange={handleChange} />
                        </FormControl>
                    </>
                )}

                {form.role === 'enseignant' && (
                    <>
                        <FormControl isRequired mb={4}>
                            <FormLabel>Matricule Enseignant</FormLabel>
                            <Input name="matricule_enseignant" value={form.matricule_enseignant} onChange={handleChange} />
                        </FormControl>

                        <FormControl isRequired mb={4}>
                            <FormLabel>Spécialité</FormLabel>
                            <Input name="specialite" value={form.specialite} onChange={handleChange} />
                        </FormControl>
                    </>
                )}

                <Button type="submit" colorScheme="teal" width="full">S’inscrire</Button>
            </form>
        </Box>
    );
};

export default Register;
