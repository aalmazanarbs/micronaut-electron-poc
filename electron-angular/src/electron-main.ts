import { app, BrowserWindow } from 'electron';
import * as path from 'path';
import * as url from 'url';

let mainWindow: Electron.BrowserWindow;

function createWindow() {
    mainWindow = new BrowserWindow({
        width: 1281,
        height: 800,
        minWidth: 1281,
        minHeight: 800,
        // backgroundColor: '#3ecf7d',
        icon: path.join(__dirname, 'electron-angular/assets/icons/app_icon.png'),
        webPreferences: {
            nodeIntegration: false,
            allowRunningInsecureContent: false,
            webSecurity: true
        }
    });

    // and load the index.html of the app.
    mainWindow.loadURL(
        url.format({
            pathname: path.join(__dirname, 'electron-angular/index.html'),
            protocol: 'file:',
            slashes: true
        })
    );

    mainWindow.webContents.on('new-window', (e, link) => {
        e.preventDefault();
        require('electron').shell.openExternal(link);
    });

    // Open the DevTools.
    mainWindow.webContents.openDevTools();

    mainWindow.on('closed', () => {
        mainWindow = null;
    });
}

app.on('ready', createWindow);

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit();
    }
});

app.on('activate', () => {
    if (mainWindow === null) {
        createWindow();
    }
});
