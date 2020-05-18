module.exports = {
	root: true,
	env: {
		node: true,
	},
	extends: ["eslint:recommended", "plugin:vue/essential"],
	plugins: ["prettier"],
	rules: {
		"vue/max-attributes-per-line": 0,
		"vue/singleline-html-element-content-newline": 0,
		"vue/multiline-html-element-content-newline": 0,
		"no-console": process.env.NODE_ENV === "production" ? "error" : "off",
		"no-debugger": process.env.NODE_ENV === "production" ? "error" : "off",
		"no-unused-vars": 1,
		"no-undef": 0,
		"vue/no-unused-components": "off",
		"no-unused-components": "off",
		"no-useless-escape": "off",
		"no-prototype-builtins": "off",
	},
	parserOptions: {
		parser: "babel-eslint",
	},
};
