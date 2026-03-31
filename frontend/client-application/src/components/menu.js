import React, { useEffect, useState } from "react";
import axios from "axios";
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import FavoriteIcon from '@mui/icons-material/Favorite';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Grid from '@mui/material/Grid';
import { red } from '@mui/material/colors';
import { alpha } from '@mui/material/styles';

export default function DishReviewCard({ setCartCount, setCartItems }) {
 
  const [menu, setMenu] = useState([])
  const [addedItems, setAddedItems] = useState({});

  useEffect(() => {
    axios.get('http://localhost:8081/api/v1/menu')
      .then(response => {
        setMenu(response.data);
      })
      .catch(err => console.error(err));
  }, []);

  return (
    <Stack spacing={2} sx={{ padding: 2, pb: 10 }}>
      <Grid container spacing={2} columns={{ xs: 12, sm: 12, md: 12, lg: 12 }}>
      
    
    {menu.length === 0 ? (
      <p>Loading menu...</p>
    ) : (
      menu.map((item) => (
        <Grid item xs={12} sm={6} md={4} lg={3} sx={{ display: 'flex', minWidth: 0 }}>
   
   <Card sx={{ width: 350, flexShrink: 0, display: 'flex', flexDirection: 'column' }} >
      
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            R
          </Avatar>
        }
  
        title= {item.dishName}
        subheader=""
      />
      <CardMedia
        component="img"
        height="194"
        image="/static/images/cards/paella.jpg"
        alt=""
        sx={{ width: '100%', height: 'auto' }}
      />
      <CardContent sx={{ flexGrow: 1 }}>
        <Typography variant="body2" sx={{ color: 'text.secondary' }}>
          {item.ingredients}
        </Typography>
      </CardContent>

      <CardActions  sx={{ justifyContent: 'space-between' }}>
        <IconButton aria-label="add to favorites">
          <FavoriteIcon />
        </IconButton>
        
        <Button
  onClick={() => {
    setCartCount(count => count + 1);
    setAddedItems(prev => ({
      ...prev,
      [item.id]: true,
    }));
    setCartItems(prev => [...prev, { ...item, quantity: 1 }]);
  }} variant="outlined" disabled={addedItems[item.id]}
  sx={{
    color: red[500],
    borderColor: red[500],
    '&:hover': {
      backgroundColor: alpha(red[500], 0.08),
      borderColor: red[500],
    },
  }}>{addedItems[item.id] ? "Added" : "Add"}</Button>
      </CardActions>

    </Card>
    </Grid>
      ))  
  )}
    
    </Grid>
    </Stack>
  );
}

