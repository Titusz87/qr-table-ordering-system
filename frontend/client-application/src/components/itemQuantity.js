import IconButton from '@mui/material/IconButton';
import ArrowCircleUpOutlinedIcon from '@mui/icons-material/ArrowCircleUpOutlined';
import ArrowCircleDownOutlinedIcon from '@mui/icons-material/ArrowCircleDownOutlined';

export function AddQuantityIconButton({ onClick }) {
    return (
      <IconButton onClick={onClick}>
        <ArrowCircleUpOutlinedIcon fontSize="large" />
      </IconButton>
    );
  }
  
  export function DecreaseQuantityIconButton({ onClick }) {
    return (
      <IconButton onClick={onClick}>
        <ArrowCircleDownOutlinedIcon fontSize="large" />
      </IconButton>
    );
  }