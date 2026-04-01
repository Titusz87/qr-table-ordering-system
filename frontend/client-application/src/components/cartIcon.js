import { styled } from '@mui/material/styles';
import IconButton from '@mui/material/IconButton';
import Badge, { badgeClasses } from '@mui/material/Badge';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCartOutlined';

const CartBadge = styled(Badge)`
  & .${badgeClasses.badge} {
    top: -28px;
    right: -12px;
    transform: scale(1.1);
  }
`;

export default function CartIconButton({cartCount, onClick}) {
  return (
    <IconButton onClick={onClick}>
      <ShoppingCartIcon fontSize="small" />
      <CartBadge badgeContent={cartCount} color="error" overlap="circular" />
    </IconButton>
  );
}
