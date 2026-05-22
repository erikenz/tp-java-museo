import tailwind from "@astrojs/tailwind";
import { defineConfig } from "astro/config";

// https://astro.build/config
export default defineConfig({
	integrations: [tailwind()],
	server: {
		proxy: {
			"/api": {
				target: "http://localhost:8080",
				changeOrigin: true,
			},
		},
	},
});
