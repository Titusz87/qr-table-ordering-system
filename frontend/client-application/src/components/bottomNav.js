import * as React from 'react';
import BottomNavigation from '@mui/material/BottomNavigation';
import BottomNavigationAction from '@mui/material/BottomNavigationAction';
import RestoreIcon from '@mui/icons-material/Restore';
import FavoriteIcon from '@mui/icons-material/Favorite';
import CartIconButton from './cartIcon';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Slide from '@mui/material/Slide';
import { AddQuantityIconButton, DecreaseQuantityIconButton } from './itemQuantity';
import { Box, Typography } from "@mui/material";


const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="up" ref={ref} {...props} />;
});

export default function BottomNavBar({ 
  cartCount, 
  cartItems,
  increaseQty,
  decreaseQty 
}) {
  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };


  return (
    <>
    <Box
    sx={{
      position: "fixed",
      bottom: 0,
      left: 0,
      right: 0,
      zIndex: (theme) => theme.zIndex.drawer + 1,
    }}
    >
      <BottomNavigation>
        <BottomNavigationAction label="Recents" icon={<RestoreIcon />} />
        <BottomNavigationAction label="Favorites" icon={<FavoriteIcon />} />
        <BottomNavigationAction label="Cart" icon={<CartIconButton cartCount={cartCount} onClick={() => setOpen(true)} />} />
      </BottomNavigation>
    </Box>

     <Dialog
     fullWidth maxWidth="md"
     open={open}
     slots={{
      transition: Transition,
    }}
    keepMounted
     onClose={handleClose}
   >
     <DialogTitle>Table #3 <br /> <br />  </DialogTitle>

     <DialogContent>
       {cartItems.length === 0 ? (
         <DialogContentText>No items selected</DialogContentText>
       ) : (
         cartItems.map((item, index) => (
          <Box
        key={index}
        sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
          mb: 1,
        }}
      >
        {/* Item name */}
        <Typography>{item.dishName}</Typography>

        {/* Quantity controls */}
        <Box sx={{ display: "flex", alignItems: "center" }}>
          <DecreaseQuantityIconButton
            onClick={() => decreaseQty(index)}
          />

          <Typography sx={{ mx: 1 }}>
            {item.quantity}
          </Typography>

          <AddQuantityIconButton
            onClick={() => increaseQty(index)}
          />
        </Box>
      </Box>
    ))
  )}
</DialogContent>
<DialogContent> 
<Typography> Total: A$ {}</Typography>
  
  
  </DialogContent>

     <DialogActions>
       <Button onClick={handleClose}>Close</Button>
       <Button variant="contained"  disabled={cartItems.length === 0}>Send Order</Button>
     </DialogActions>
   </Dialog>
   </>
  );
}
