public record CurrencyResponse(
        String result,
        String base_code,
        String target_code,
        Double conversion_rate
) {}