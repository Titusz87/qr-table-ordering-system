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

export default function RecipeReviewCard() {
 
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
    
    <div style={{ display: 'flex', flexWrap: 'wrap', gap: '16px' }}>
    {menu.length === 0 ? (
      <p>Loading menu...</p>
    ) : (
      menu.map((item) => (
    
      <Card key={item.id} sx={{ maxWidth: 345 }}>
      
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
      />
      <CardContent>
        <Typography variant="body2" sx={{ color: 'text.secondary' }}>
          {item.ingredients}
        </Typography>
      </CardContent>

      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <FavoriteIcon />
        </IconButton>
        <Button loading variant="outlined" loadingPosition="end">
          Ordering
        </Button>
      </CardActions>

    </Card>
      ))  
  )}
    </div>
  );
}

