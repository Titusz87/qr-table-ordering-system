import React, { useState } from "react";
import axios from "axios";
import BottomNavigation from '@mui/material/BottomNavigation';
import BottomNavigationAction from '@mui/material/BottomNavigationAction';
import RestoreIcon from '@mui/icons-material/Restore';
import FavoriteIcon from '@mui/icons-material/Favorite';
import CartIconButton from './cartIcon';
import Button from '@mui/material/Button';
import LoadingButton from '@mui/lab/LoadingButton';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Slide from '@mui/material/Slide';
import { AddQuantityIconButton, DecreaseQuantityIconButton } from './itemQuantity';
import { Box, Typography } from "@mui/material";
import { grey } from '@mui/material/colors';
import api from "../api";


const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="up" ref={ref} {...props} />;
});

export default function BottomNavBar({ 
  cartCount, 
  setCartCount,
  cartItems,
  setCartItems,
  increaseQty,
  decreaseQty,
  billTotal
}) {
  const [cartOpen, setCartOpen] = useState(false);
  const [recentOpen, setRecentOpen] = useState(false);
  const [loading, setLoading] = useState(false);
  const [orderSummary, setOrderSummary] = useState(null);
  
  const handleSendOrder = async () => {
    setLoading(true);
  
    const payload = {
      userName: "Daniel123",
      items: cartItems.map(item => ({
        dishName: item.dishName,
        quantity: item.quantity
      })),
      paymentType: "APPLE_PAY",
      comment: ""
    };
  
    try {
      const response = await api.post("/orders", payload);
      setOrderSummary(response.data);

      console.log("Order sent:", orderSummary);
  
      setTimeout(() => {
      //clear cart
      setCartItems([]);
      setCartCount(0);
  
      setLoading(false);
      setCartOpen(false);
    }, 2000);

    } catch (err) {
      console.error("Failed to send order:", err.response?.data || err);
      setLoading(false);
    }
  };

  const isDisabled =
    cartItems.length === 0 ||
    cartItems.every(item => item.quantity === 0) ||
    loading; 

  const handleClickOpen = () => {
    setCartOpen(true);
  };

  const handleClose = () => {
    setCartOpen(false);
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
        <BottomNavigationAction label="Recents" icon={<RestoreIcon onClick={() => setRecentOpen(true)}/>} />
        <BottomNavigationAction label="Favorites" icon={<FavoriteIcon />} />
        <BottomNavigationAction label="Cart" icon={<CartIconButton cartCount={cartCount} onClick={() => setCartOpen(true)} />} />
      </BottomNavigation>
    </Box>

     <Dialog
     fullWidth maxWidth="md"
     open={cartOpen}
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
<Typography> Total: A$ {billTotal}</Typography>
  
  </DialogContent>

     <DialogActions>
     <Button
  onClick={() => {
    const filtered = cartItems.filter(item => item.quantity > 0);
    setCartItems(filtered);
    setCartCount(filtered.length);
    handleClose();
  }}
  disabled={loading}
  variant="outlined"
    sx={{
      color: grey[700],
      borderColor: grey[700]}}
  >Close
  </Button>
       <LoadingButton variant="contained"  
        color="error"
        onClick={handleSendOrder}
        disabled={isDisabled}
        loading={loading}
        loadingPosition="end"
        sx={{ minWidth: 140 }}
        >
           {loading ? "Pending" : "Send Order" }
          </LoadingButton>
     </DialogActions>
   </Dialog>

   <Dialog
  fullWidth
  maxWidth="md"
  open={recentOpen}
  onClose={() => setRecentOpen(false)}
>
  <DialogTitle>Recent Order</DialogTitle>

  <DialogContent>
    {!orderSummary ? (
      <DialogContentText>No recent order</DialogContentText>
    ) : (
      <>

        {orderSummary.items?.map((item, index) => (
          <Box key={index} sx={{ display: "flex", justifyContent: "space-between", mb: 1 }}>
            <Typography>{item.dishName}</Typography>
            <Typography>x{item.quantity}</Typography>
          </Box>
        ))}
        <br></br>
        <br></br>
        <Typography> Total: A$ {orderSummary.total}</Typography>
      </>
    )}
  </DialogContent>

  <DialogActions>
    <Button
    onClick={() => setRecentOpen(false)}
    variant="outlined"
    sx={{
      color: grey[700],
      borderColor: grey[700]}}
    >Close</Button>
  </DialogActions>
</Dialog>

   </>
  );
}
