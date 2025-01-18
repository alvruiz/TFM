import { test, expect } from '@playwright/test';


test('iniciar sesión con credenciales válidas', async ({ page }) => {
    await page.goto('http://localhost:3000/signInLogin');
    await page.fill('input[name="email"]', 'admin@admin.com');
    await page.fill('input[name="password"]', 'adminadmin');
    await page.click('button[type="submit"]');
    await expect(page).toHaveURL('http://localhost:3000');
});



test('iniciar sesión con credenciales incorrectas muestra un mensaje de error', async ({ page }) => {
    await page.goto('http://localhost:3000/signInLogin');
    await page.fill('input[name="email"]', 'wrong@admin.com');
    await page.fill('input[name="password"]', 'wrongpassword');
    await page.click('button[type="submit"]');
    const errorMessage = page.getByText('Error al iniciar sesión: Credenciales incorrectas');
    await expect(errorMessage).toBeVisible();
});

test('iniciar sesión con contraseña corta muestra mensaje de error', async ({ page }) => {
    await page.goto('http://localhost:3000/signInLogin');
    await page.fill('input[name="email"]', 'admin@admin.com');
    await page.fill('input[name="password"]', 'short'); // Contraseña con menos de 8 caracteres
    await page.click('button[type="submit"]');
    const errorMessage = page.getByText('La contraseña debe tener al menos 8 caracteres.');
    await expect(errorMessage).toBeVisible();
});


test('registrarse con campos de formulario', async ({ page }) => {
    await page.goto('http://localhost:3000/signInLogin');
    await page.click('text="¿No tienes cuenta? Regístrate"');
    await page.fill('input[name="email"]', 'testuser@example.com');
    await page.fill('input[name="password"]', 'password123');
    await page.fill('input[name="name"]', 'John');
    await page.fill('input[name="surname"]', 'Doe');
    await page.fill('input[name="age"]', '30');
    await page.click('[data-testid="gender-select"]');
    await page.click('text="Hombre"');

    await page.fill('input[name="imageUrl"]', 'https://example.com/image.jpg');
    await page.click('button[type="submit"]');
    await expect(page).toHaveURL('http://localhost:3000');
    const welcomeMessage = await page.locator('text=Registro exitoso');
    await expect(welcomeMessage).toBeVisible();
});
