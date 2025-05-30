from flask import Flask, request, jsonify
import joblib

app = Flask(__name__)

# Load pre-trained model
model = joblib.load("model.pkl")

@app.route("/predict", methods=["POST"])
def predict():
    data = request.get_json()
    features = data.get("features", [])
    
    if not features or not isinstance(features, list):
        return jsonify({"error": "Missing or invalid 'features'"}), 400

    try:
        prediction = model.predict([features])[0]
        return jsonify({"prediction": str(prediction)})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == "__main__":
    app.run(debug=True)