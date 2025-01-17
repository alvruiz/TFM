module.exports = {
	overrides: [
		{
			files: ["*.test.ts", "*.test.tsx"], // Asegúrate de que solo se aplique a las pruebas de React Testing Library
			rules: {
				"testing-library/prefer-screen-queries": "off", // Desactiva la regla para estas pruebas
			},
		},
	],
};
