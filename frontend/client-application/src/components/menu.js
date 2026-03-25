import React, { useEffect, useState } from "react";
import axios from "axios";
import { TableContainer } from "@mui/material";
import { Paper } from '@mui/material';

export default function Menu(){

    const [menu, setMenu] = useState(null)

    useEffect(() => {
        axios.get('http://localhost:8081/api/v1/menu').then(response => {
            setMenu(response.data)
            console.log(response.data)
        })
}, []);

    return (
        <TableContainer component={Paper} style={{with:'70%'}}>

        </TableContainer>
    )
}
