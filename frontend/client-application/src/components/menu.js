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
import { red } from '@mui/material/colors';
import FavoriteIcon from '@mui/icons-material/Favorite';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Grid from '@mui/material/Grid';

export default function DishReviewCard() {
 
  const [menu, setMenu] = useState([])

  useEffect(() => {
    axios.get('http://localhost:8081/api/v1/menu')
      .then(response => {
        setMenu(response.data);
        console.log(response.data);
      })
      .catch(err => console.error(err));
  }, []);

  return (
    <Stack spacing={2} sx={{ padding: 2 }}>
      <Grid container spacing={2} columns={{ xs: 12, sm: 12, md: 12, lg: 12 }}>
      
    
    {menu.length === 0 ? (
      <p>Loading menu...</p>
    ) : (
      menu.map((item) => (
        <Grid item xs={12} sm={6} md={4} lg={3} sx={{ display: 'flex' }}>
   
   <Card sx={{ minWidth: 250, width: '100%', display: 'flex', flexDirection: 'column' }} >
      
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            R
          </Avatar>
        }
  
        title= {item.dishName}
        subheader="September 14, 2016"
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

      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <FavoriteIcon />
        </IconButton>
        <Button loading variant="outlined" loadingPosition="end">
          Pending
        </Button>
      </CardActions>

    </Card>
    </Grid>
      ))  
  )}
    
    </Grid>
    </Stack>
  );
}

