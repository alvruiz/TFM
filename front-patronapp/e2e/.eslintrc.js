module.exports = {
	overrides: [
		{
			files: ["*.test.ts", "*.test.tsx"],
			rules: {
				"testing-library/prefer-screen-queries": "off",
			},
		},
	],
};
