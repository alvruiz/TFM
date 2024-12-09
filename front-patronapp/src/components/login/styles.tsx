import colors from '../../utils/colors';

const styles: { [key: string]: React.CSSProperties } = {
    root: {
        display: 'flex',
        flexDirection: 'row',
    },
    videoBackground: {
        width: '100%',
        objectFit: 'cover',
        height: '100vh',

    },
    loginContainer: {
        paddingLeft: 8,
        paddingRight: 8,
        backgroundColor: 'rgba(246, 232, 214, 0.9)',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        zIndex: 1,
        height: '100vh',

    },
    imageContainer: {
        position: 'absolute',
        top: '50%',
        transform: 'translateY(-50%)',
        width: '100%',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        textAlign: 'center',
        color: 'white',
        opacity: 0.9,
        zIndex: 1,
    },
    title: {
        color: colors.textDark,
        fontSize: '2.5rem',
        fontWeight: 'bold',
        marginBottom: 2,
    },
    title2: {
        fontSize: '2.5rem',
        fontWeight: 'bold',
        marginBottom: 2,
    },
    subtitle: {
        fontSize: '1.4rem',
    },
    link: {
        fontSize: '0.9rem',
        color: colors.textDark,
        textDecoration: 'none',
        marginTop: 1,
        display: 'block',
        textAlign: 'center',
    },
    formControl: {
        marginBottom: 2,
    },
    toggleButton: {
        marginTop: 2,
        fontSize: '0.9rem',
        textAlign: 'center',
        color: colors.textDark,

    },
    logoContainer: {
        position: 'absolute',
        top: 20,
        right: 20,
        zIndex: 2,
    },
    logo: {
        height: '100px',
        opacity: 0.9,
    },

};

export default styles;