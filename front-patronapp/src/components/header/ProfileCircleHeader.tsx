import { Avatar } from "@mui/material";
import { useNavigate } from "react-router-dom";

const ProfileCircle = ({ imageUrl, altText, width, height }) => {
    const navigate = useNavigate();
    return (
        <Avatar
            src={imageUrl}
            onClick={() => navigate('/editProfile')}
            alt={altText}
            sx={{
                width: width,
                height: height,
                border: '2px solid white',
                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)',
                cursor: 'pointer',
            }}
        />
    );
};

export default ProfileCircle;