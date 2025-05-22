import React, { useState } from 'react';
import {
    Box, Button, FormControl, FormLabel, Input, Heading, useToast, Text, Link as ChakraLink
} from '@chakra-ui/react';
import { Link } from 'react-router-dom';  // <-- importer Link
import axios from 'axios';

const Login = () => {
    const [form, setForm] = useState({ email: '', motDePasse: '' });
    const toast = useToast();

    const handleChange = e => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async e => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8081/api/auth/login', form);
            const token = response.data.token;
            localStorage.setItem('token', token);
            toast({
                title: 'Connexion réussie.',
                status: 'success',
                duration: 3000,
                isClosable: true
            });
            // redirection par ex.
            // window.location.href = '/dashboard';
        } catch (error) {
            toast({
                title: 'Erreur lors de la connexion',
                description: error.response?.data?.message || error.message,
                status: 'error',
                duration: 3000,
                isClosable: true
            });
        }
    };

    return (
        <Box maxW="md" mx="auto" mt={10} p={6} borderWidth="1px" borderRadius="lg">
            <Heading mb={6} size="lg" textAlign="center">Connexion</Heading>
            <form onSubmit={handleSubmit}>
                <FormControl isRequired mb={4}>
                    <FormLabel>Email</FormLabel>
                    <Input type="email" name="email" value={form.email} onChange={handleChange} />
                </FormControl>

                <FormControl isRequired mb={6}>
                    <FormLabel>Mot de passe</FormLabel>
                    <Input type="password" name="motDePasse" value={form.motDePasse} onChange={handleChange} />
                </FormControl>

                <Button type="submit" colorScheme="teal" width="full" mb={4}>Se connecter</Button>
            </form>

            <Text textAlign="center">
                Pas encore de compte ?{' '}
                <ChakraLink as={Link} to="/register" color="teal.500" fontWeight="bold">
                    Inscrivez-vous
                </ChakraLink>
            </Text>
        </Box>
    );
};

export default Login;
