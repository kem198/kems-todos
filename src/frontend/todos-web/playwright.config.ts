import { defineConfig, devices } from "@playwright/test";

const isCI = !!process.env.CI;

export default defineConfig({
  testDir: "tests/e2e",
  testMatch: "**/*.spec.ts",
  reporter: [
    ["html", { outputFolder: "playwright-report", open: "never" }],
    ["list"],
  ],
  retries: isCI ? 2 : 0,
  fullyParallel: true,
  use: {
    baseURL: "http://localhost:3000",
    screenshot: isCI ? "only-on-failure" : "on",
    video: isCI ? "retain-on-failure" : "on",
    trace: isCI ? "retain-on-failure" : "on",
  },
  projects: [
    {
      name: "chromium",
      use: { ...devices["Desktop Chrome"] },
    },
  ],
});
