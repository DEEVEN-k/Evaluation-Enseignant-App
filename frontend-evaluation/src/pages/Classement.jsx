import React, { useEffect, useState } from 'react';
import {
    Box, Heading, Table, Thead, Tbody, Tr, Th, Td, Spinner, Text
} from '@chakra-ui/react';
import axios from 'axios';

const Classement = () => {
    const [classement, setClassement] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        axios.get('http://localhost:8081/api/classement')
            .then((response) => {
                setClassement(response.data);
                setLoading(false);
            })
            .catch((error) => {
                console.error('Erreur lors de la récupération du classement', error);
                setLoading(false);
            });
    }, []);

    return (
        <Box p={6}>
            <Heading mb={6}>Classement des Enseignants</Heading>
            {loading ? (
                <Spinner size="xl" />
            ) : (
                <>
                    {classement.length === 0 ? (
                        <Text>Aucun enseignant évalué pour le moment.</Text>
                    ) : (
                        <Table variant="simple">
                            <Thead>
                                <Tr>
                                    <Th>Rang</Th>
                                    <Th>Nom</Th>
                                    <Th>Prénom</Th>
                                    <Th>Spécialité</Th>
                                    <Th>Note Moyenne</Th>
                                </Tr>
                            </Thead>
                            <Tbody>
                                {classement.map((enseignant, index) => (
                                    <Tr key={enseignant.id}>
                                        <Td>{index + 1}</Td>
                                        <Td>{enseignant.nom}</Td>
                                        <Td>{enseignant.prenom}</Td>
                                        <Td>{enseignant.specialite}</Td>
                                        <Td>{enseignant.note_moyenne.toFixed(2)}</Td>
                                    </Tr>
                                ))}
                            </Tbody>
                        </Table>
                    )}
                </>
            )}
        </Box>
    );
};

export default Classement;
