import { dirname } from "path";
import { fileURLToPath } from "url";
import { FlatCompat } from "@eslint/eslintrc";
import eslintConfigPrettier from "eslint-config-prettier/flat";
import eslintPluginUnicorn from "eslint-plugin-unicorn";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const compat = new FlatCompat({
  baseDirectory: __dirname,
});

const eslintConfig = [
  ...compat.extends("next/core-web-vitals", "next/typescript"),
  {
    ignores: [
      "node_modules/**",
      ".next/**",
      "out/**",
      "build/**",
      "next-env.d.ts",
    ],
  },
];

export default [
  eslintConfig,
  eslintPluginUnicorn.configs.recommended,
  {
    rules: {
      ...eslintPluginUnicorn.configs.recommended.rules,
      "unicorn/consistent-function-scoping": "off",
      "unicorn/numeric-separators-style": "off",
      "unicorn/prevent-abbreviations": "off",
      "unicorn/switch-case-braces": "off",
      "unicorn/no-static-only-class": "off",
      "unicorn/no-useless-undefined": "off",
    },
  },
  ,
  eslintConfigPrettier,
];
